# Dungeon Lab
Wandering maze simulation using *Drunken Stumble* algorithim. Plays like a top down dungeon crawler.

## Generation
All generation takes place by the Drunken Stumble class, by calling *returnMatrix()* we are able to get the proper generated map, based on the principles defined in the algorithm.

## Algorithim
The key principle is the creation of *walkers* which walk across the map (matrix) and have predefined probibilites to change direction, spawn another walker, or die. If any of these thresholds are met the walker will do the appropriate action. The walkers flip direction when the reach a wall, and all die once 25% of the map is filled. Afterwards the simulations removes single space gaps in the maze, and creates a border wall around the whole thing.
