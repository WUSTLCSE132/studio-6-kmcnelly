package studio6;

import java.util.Scanner;

import jssc.*;
import studio6.SerialComm;

public class SerialComm {

	SerialPort port;

	private boolean debug;  // Indicator of "debugging mode"


	// This function can be called to enable or disable "debugging mode"
	void setDebug(boolean mode) {
		debug = mode;
	}	


	// Constructor for the SerialComm class
	public SerialComm(String name) throws SerialPortException {
		port = new SerialPort(name);		
		port.openPort();
		port.setParams(SerialPort.BAUDRATE_9600,
				SerialPort.DATABITS_8,
				SerialPort.STOPBITS_1,
				SerialPort.PARITY_NONE);

		debug = false; // Default is to NOT be in debug mode
	}

	// TODO: Add writeByte() method from Studio 5
	public void writeByte(byte input){
		try {
			port.writeByte(input);
		} catch (SerialPortException e) {
			e.printStackTrace();
		}
		if(debug)
		{
			String inString = Byte.toString(input);
			System.out.println("input: " + inString);
			System.out.println(String.format("0x", Byte.parseByte(inString)));
		}
	}

	// TODO: Add available() method
	public boolean available(){
		try {
			if(port.getInputBufferBytesCount() > 0)
			{
				return true;
			}
		} catch (SerialPortException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return false;
	}

	// TODO: Add readByte() method	
	public byte readByte() throws SerialPortException{
		byte[] bytesIn = port.readBytes();
		byte result = bytesIn[0];
		return result;	
	}

	// TODO: Add a main() method
	static void main(String[] args){
		SerialComm myPort;
		try {
			myPort = new SerialComm("/dev/cu.usbserial-DN02BGGE");
			Scanner sc = new Scanner(System.in);
			while(myPort.available())
			{
				try {
					myPort.readByte();
				} catch (SerialPortException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		} catch (SerialPortException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
	}
}
