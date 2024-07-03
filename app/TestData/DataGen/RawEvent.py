import random
import string

# Define possible segments for the rawevent
l

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
