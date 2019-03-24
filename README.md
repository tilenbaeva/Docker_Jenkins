# Docker_Jenkins
*** 1 job ***
- Builds the image
- Pushes to DockerHub

*** 2 job ***
- Pulls the image
- Removes old container
- Creates the container (so when you will deploy new container will be automatically deployed, and old one will be deleted)

--> We used variables for ${IP} and ${VER}  [VER= version], ${PORT} for ports <--
