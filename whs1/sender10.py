import socket
# "t1", 30
# "t2", 20
# "t3", 30
# "h1", 50
# "t4", 30
# "t5", 30
# "t6", 30
# "h2", 60

sock = socket.socket(socket.AF_INET, socket.SOCK_DGRAM)
sock.sendto(b"sensor_id=t1; value=30", ("127.0.0.1", 3344))
sock.sendto(b"sensor_id=h1; value=40", ("127.0.0.1", 3355))
sock.sendto(b"sensor_id=h1; value=70", ("127.0.0.1", 3355))
sock.sendto(b"sensor_id=h2; value=70", ("127.0.0.1", 3355))
sock.sendto(b"sensor_id=h2; value=50", ("127.0.0.1", 3355))
sock.sendto(b"sensor_id=h2; value=80", ("127.0.0.1", 3355))
sock.sendto(b"sensor_id=h2; value=70.5", ("127.0.0.1", 3355))
sock.sendto(b"sensor_id=t5; value=50.5", ("127.0.0.1", 3344))