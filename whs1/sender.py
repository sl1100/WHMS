import socket

sock = socket.socket(socket.AF_INET, socket.SOCK_DGRAM)
# sock.sendto(b"sensor_id=t1; value=30", ("127.0.0.1", 3344))
# sock.sendto(b"sensor_id=h1; value=40", ("127.0.0.1", 3355))
sock.sendto(b"sensor_id=h2; value=70", ("127.0.0.1", 3355))