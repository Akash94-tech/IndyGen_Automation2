import random

# List of possible transport protocols with their 3-character representations
transport_protocols = [
    ("TCP", "Transmission Control Protocol"),
    ("UDP", "User Datagram Protocol"),
    ("SCT", "Stream Control Transmission Protocol"),
    ("FTP", "File Transfer Protocol"),
    ("SSH", "Secure Shell Protocol"),
    ("TLS", "Transport Layer Security"),
    ("HTTP", "Hypertext Transfer Protocol"),
    ("POP", "Post Office Protocol"),
    ("IMAP", "Internet Message Access Protocol"),
    ("SMTP", "Simple Mail Transfer Protocol"),
    ("DNS", "Domain Name System"),
    ("NTP", "Network Time Protocol"),
    ("RPC", "Remote Procedure Call"),
    ("TEL", "Telnet Protocol"),
    ("DCC", "Distributed Checksum Clearinghouse"),
    ("ICP", "Internet Cache Protocol"),
    ("LDP", "Label Distribution Protocol"),
    ("L2F", "Layer 2 Forwarding Protocol"),
    ("L2T", "Layer 2 Tunneling Protocol"),
    ("PPP", "Point-to-Point Protocol"),
    ("PPT", "Point-to-Point Tunneling Protocol"),
    ("RTB", "Routing Table Protocol"),
    ("RIP", "Routing Information Protocol"),
    ("BGP", "Border Gateway Protocol"),
    ("OSI", "Open Systems Interconnection")
]

def generate_transport_protocol():
    # Randomly select a transport protocol
    protocol = random.choice(transport_protocols)
    return protocol[0]

# Generate a few sample transport protocols
for _ in range(5):
    print(generate_transport_protocol())
