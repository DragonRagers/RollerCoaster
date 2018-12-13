# RollerCoaster
We're making a custom rollercoaster simulator

PROPOSAL:

We will produce an interactive model of a rollercoaster, where the user can produce their own ride with loops, curves, and inclines. A model is needed to simulate if a rollercoaster works, which means it will calculate if there is enough potential energy for it to complete the loops, go over hills, or move. This means that we only really care about when it’s going downhill as it will be pulled uphill with a motor. We should try to make it so that there is a seamless transition from regular motion to the motor-pulling motion. 

The model would ideally have two types of views: top-down and from the sides (the cardinal directions). The sideview could be troublesome to implement, so our main goal is just to have a functional top-down model. The user interface would have modular track pieces to attach to each other to form a ride. Such pieces may include straight lines, curves, loops, hills, and more. 

