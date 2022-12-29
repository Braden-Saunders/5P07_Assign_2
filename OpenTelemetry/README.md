# Assignment 2 Part 2 - OpenTelementry
Braden Saunders, Student # 6283725

## The Applications
The applications (client and server) use the Apache HttpComponents library to facilitate HTTP communication.
The server application creates an HTTP server listening on port 7777, each time the server receives an HTTP post request, it creates a new thread to handle the data. The server writes any data it receives to a file called ouput.txt, it uses a synchronized block to ensure that multiple threads cannot access the output file simultaneously.
The client application creates 10 threads and reads in 10 files (which are named file1.txt through to file 10.txt) in parallel, as the files are read in they're sent to the server using an HTTP request. The client assumes that the server is running on the same computer as the client (localhost port 7777). 
The code is annotated with @WithSpan annotations which indicates to OpenTelementry which methods should be included in the tracing.

## OpenTelementry
I used the OpenTelementry java agent to export the tracing data from the applications, and I used [Jaeger UI](https://www.jaegertracing.io/) as my collector to visualize the tracing data. Screenshots of the output (for both the client and server programs) are in this repository.
