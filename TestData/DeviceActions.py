from enum import Enum
import random
from faker import Faker

# Initialize Faker
fake = Faker()

# Define an Enum for device actions
class DeviceAction(Enum):
    IDS_ACTION_WOULD_BLOCK = "IDS_ACTION_WOULD_BLOCK"
    NAN = "nan"
    DROP = "Drop"
    DROP_LOWERCASE = "drop"
    HTTPS_BYPASS = "HTTPS Bypass"
    NA = "NA"
    DETECT = "Detect"
    PREVENT = "Prevent"
    DENY = "deny"
    ALLOW = "allow"

def generate_device_action():
    # Randomly select a device action from the enum
    action = random.choice(list(DeviceAction))
    return action.value

# Generate a few sample device actions
for _ in range(10):
    print(generate_device_action())