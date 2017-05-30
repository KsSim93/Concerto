import socket
import serial
import time

APORT = "/dev/ttyACM0"
serialFromArduino = serial.Serial(APORT,9600)
serialFromArduino.flushInput()

HOST = ""
PORT = 8080

s = socket.socket(socket.AF_INET, socket.SOCK_STREAM)

print('Socket created')
s.bind((HOST,PORT))
print('Socket bind complete')
s.listen(1)
print('Socket now listening')

#���� ��Ʈ�� �Լ�
def do_some_stuffs_with_input(input_string):
	return input_string

while True:
	#���� ����
	
	conn, addr = s.accept()
	print("Connected by",addr)
	
	while True:
		input_s = serialFromArduino.readline()
		input = str(input_s)
		print(input)
		conn.sendall(input)
		time.sleep(1)

	#����ݱ�
	conn.close()

s.close()