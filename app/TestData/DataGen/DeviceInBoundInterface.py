import random

def generate_random_deviceinboundinterface(count):
    interfaces = ['nan', 'eth2', 'daemon', 'eth3', 'eth1', 'eth7', 'eth5', 'eth4']
    random_interfaces = random.choices(interfaces, k=count)
    formatted_interfaces = '";"'.join(random_interfaces)
    formatted_string = f'"{formatted_interfaces}"'
    return formatted_string

# Example usage
if __name__ == "__main__":
    count = 10
    deviceinboundinterface = generate_random_deviceinboundinterface(count)
    print("Generated random deviceinboundinterface values:")
    print(deviceinboundinterface)
