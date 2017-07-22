package org.suliga.badlands.controller;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.suliga.badlands.model.Greeting;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@RestController
@Api(tags = "My Greeting Rest Controller")
public class MainRestController {
	@ApiOperation(value = "getGreeting", nickname = "getGreeting", 
			notes="A Rest operation for getting a custom greeting")
    @GetMapping(path="/rest/greeting", produces = "application/json")
    @ApiImplicitParams({
        @ApiImplicitParam(name = "id", value = "User's Id", required=false, 
        		dataType="long", paramType="query", defaultValue="42"),
        @ApiImplicitParam(name = "name", value = "User's name", required = false, 
        		dataType = "string", paramType = "query", defaultValue="tom")
      })
    @ApiResponses(value = { 
            @ApiResponse(code = 200, message = "Normal response is 200 Success", response = Greeting.class),
            @ApiResponse(code = 401, message = "Unauthorized"),
            @ApiResponse(code = 403, message = "Forbidden"),
            @ApiResponse(code = 404, message = "Not Found"),
            @ApiResponse(code = 500, message = "Failure")}) 
	public Greeting greeting(@RequestParam(value="id", defaultValue="42") long id,
	    					 @RequestParam(value="name", defaultValue="Tom") String name) {
		return new Greeting(id, String.format("Are you %s?", name));
	}
	
	@PostMapping(path="/rest/greeting", produces="application/json")
	public Greeting greetingPost(@RequestParam(value="id", defaultValue="42") long id,
	    					     @RequestParam(value="name", defaultValue="Tom") String name) {
		return new Greeting(id, String.format("Are you %s?", name));
	}
	
	@PutMapping(path="/rest/greeting", produces="application/json")
	public Greeting greetingPut(@RequestParam(value="id", defaultValue="42") long id,
	    					     @RequestParam(value="name", defaultValue="Tom") String name) {
		return new Greeting(id, String.format("Are you %s?", name));
	}
	
	@DeleteMapping(path="/rest/greeting", produces="application/json")
	public Greeting greetingDelete(@RequestParam(value="id", defaultValue="42") long id,
	    					     @RequestParam(value="name", defaultValue="Tom") String name) {
		return new Greeting(id, String.format("Are you %s?", name));
	}

	@ApiOperation(value = "secretGreeting", nickname = "getSecretGreeting", 
			notes="A Rest operation for getting a secret greeting")
    @GetMapping(path="/rest/secret", produces = "application/json")
    @ApiImplicitParams({
        @ApiImplicitParam(name = "id", value = "User's Id", required=false, 
        		dataType="long", paramType="query", defaultValue="42"),
        @ApiImplicitParam(name = "name", value = "User's name", required = false, 
        		dataType = "string", paramType = "query", defaultValue="tom")
      })
    @ApiResponses(value = { 
            @ApiResponse(code = 200, message = "Normal response is 200 Success", response = Greeting.class) })
	public Greeting secretGreeting(@RequestParam(value="id", defaultValue="42") long id,
	    					 @RequestParam(value="name", defaultValue="Tom") String name) {
		return new Greeting(id, String.format("Are you secretly %s?", name));
	}
	
	// Example:
	// http://localhost:8080/rest/greeting?name=mike
	// http://localhost:8080/rest/greeting?id=42&name=tom
	// http://localhost:8080/swagger-ui.html
	// http://localhost:8080/swagger
	
	// curl -u admin http://localhost:8080/rest/greeting?id=18"&"name=mike
	// curl --netrc-file curl-pw.txt http://localhost:8080/rest/greeting?id=18
}






