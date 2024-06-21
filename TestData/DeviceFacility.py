import random

# Define possible segments for different parts of the facility string
prefixes = ["Main", "Backup", "Admin", "User", "Internal"]
middle_parts = ["Server", "Firewall", "Proxy", "Database", "System"]
suffixes = ["01", "02", "A", "B", "C", "1", "2"]

def generate_device_facility():
    # Randomly select a prefix, middle part, and suffix
    prefix = random.choice(prefixes)
    middle = random.choice(middle_parts)
    suffix = random.choice(suffixes)
    
    # Combine the parts to form the facility string
    facility_string = f"{prefix}_{middle}_{suffix}"
    
    return facility_string

# Generate a few sample device facility strings
for _ in range(5):
    print(generate_device_facility())
