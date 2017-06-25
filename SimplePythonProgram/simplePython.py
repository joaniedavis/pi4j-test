from ABE_helpers import ABEHelpers
from ABE_IoPi import IoPi
 
class ReadGPIO:
    
    def run(self):

        bus = IoPi(i2c_bus, 0x20)
 
bus.set_pin_direction(1, 0)  # set pin 1 as an output
 
bus.write_pin(8, 1)  # turn on the led on pin 1

print 'Hello world'