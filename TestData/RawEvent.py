import random
import string

# Define possible segments for the rawevent
actions = ["ALLOW", "BLOCK", "DROP", "DETECT", "PREVENT"]
sources = ["192.168.1.1", "10.0.0.1", "172.16.0.1", "192.168.0.1", "10.0.0.2"]
destinations = ["192.168.1.2", "10.0.0.2", "172.16.0.2", "192.168.0.2", "10.0.0.3"]
protocols = ["TCP", "UDP", "ICMP", "HTTP", "HTTPS"]
events = ["CONNECTION_ATTEMPT", "LOGIN_SUCCESS", "FILE_DOWNLOAD", "FILE_UPLOAD", "ACCESS_DENIED"]
users = ["user1", "admin", "guest", "user2", "service"]

def generate_rawevent():
    action = random.choice(actions)
    source = random.choice(sources)
    destination = random.choice(destinations)
    protocol = random.choice(protocols)
    event = random.choice(events)
    user = random.choice(users)
    
    # Create a rawevent string
    rawevent = (f"Event: {event} | "
                f"Action: {action} | "
                f"Source: {source} | "
                f"Destination: {destination} | "
                f"Protocol: {protocol} | "
                f"User: {user}")
    
    return rawevent

# Generate a few sample rawevent strings
for _ in range(5):
    print(generate_rawevent())
