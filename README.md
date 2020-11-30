# Assignment-secret-code

#Steps to follow:

 Java 1.8 is required to execute the code coz of stream api.
 
 All the failures has been logged to console based on the condition    
  provided.
  
  once the code executed the success response file will get generated in the class path with name success_response.txt which indicates the successfully sent the secret code to the valid customer along with his name and phone number.
  
 I have used spring boot for this so below is the schema
           GET: http://localhost:8080/api/generateCode

           RESPONSE:
                  {"total timestamp in millisecond":42}

    It returns the total timestamp after execution of all the secret codes.