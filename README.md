                           *     .--.
                                / /  `
               +               | |
                      '         \ \__,
                  *          +   '--'  *
                      +   /\
         +              .'  '.   *
                *      /======\      +
                      ;:.  _   ;
                      |:. (_)  |
                      |:.  _   |
            +         |:. (_)  |          *
                      ;:.      ;
                    .' \:.    / `.
                   / .-'':._.'`-. \
                   |/    /||\    \|
             jgs _..--"""````"""--.._
           _.-'``                    ``'-._
         -'                                '-
# Mission Control
The mission control project is the software side of a game built for Marple Presbyterian VBS 2019

Video of installation in use: https://youtu.be/O0JlTFg7W-8?si=8zNYDseUakLk6D_R

Building and testing the reciever Board: https://youtu.be/inKz8a8cP9g

The goal of the game was to complete the launch sequence for a rocket prop. Children were invited to follow the on screen prompts. If they completed the simon style puzzle, then pressed all the buttons and switches, the large launch button would begin to flash. Once pressed a webservice call would be made to process runing on the raspberry pi zero which would close a 5v relay for a few seconds.  That relay was conected to a smoke machine placed under a large foam rocket. 

The reciever board also runs a Springboot webserver that runs a page were the smoke machine could be triggers. You can see the page in action in the build video.  This was used incase the game proved too difficult for younger players the rocket would still be triggered so no one felt left out.

## Hardware Used

### Main Board

This runs the screen, annimation, buttons from the control pannel and the webservices

raspberry-pi-3-model-b
https://www.raspberrypi.com/products/raspberry-pi-3-model-b/

### Gpio extenders 
I needed two of these to control 16 buttons and two switches plus the LED for each switch
MCP23017 - i2c 16 input/output port expander
https://www.adafruit.com/product/732

### TIP122 Transitors
These are great for light controls and were used as negative side switches or the lights on all the buttons.
https://mou.sr/3HUmWfD

### Reciever board
Raspberry Pi Zero W
https://www.raspberrypi.com/products/raspberry-pi-zero-w/

### 5v relays
Used for the hardware connection between the reciever board and the smoke machine
https://konnected.io/products/1-channel-5v-relay-module-with-high-low-level-trigger




