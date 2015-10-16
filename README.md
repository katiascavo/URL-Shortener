# URL-Shortener
Dockerfile source: https://github.com/GruppoPBDMNG-1/Dockerfile.git

##Installation guide
Remember to set port 8080 into Virtual-Box

1. Start boot2docker or docker-machine
<pre>$ boot2docker start</pre>
<pre>$ boot2docker ssh</pre>

2. Clone this repository
<pre>$ git clone https://github.com/katiascavo/Dockerfile.git</pre>

3. Building of image
<pre>$ docker build --tag=gruppo_pbdmng_1/urlshortener Dockerfile</pre>

4. Create and run the container
<pre>$ docker run -d --name urlshortener -p 8080:8080 gruppo_pbdmng_1/urlshortener</pre>

5. Enter into container
<pre>$ docker exec -it urlshortener bash</pre>

6. Launch the application
<pre>$ ./start</pre>

To see the application in action open this link: http://localhost:8080 in your web broswer.
