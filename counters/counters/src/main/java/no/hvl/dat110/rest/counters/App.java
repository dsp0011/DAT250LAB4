package no.hvl.dat110.rest.counters;

import static spark.Spark.after;
import static spark.Spark.get;
import static spark.Spark.port;
import static spark.Spark.put;
import static spark.Spark.post;
import static spark.Spark.delete;



import com.google.gson.Gson;
import com.google.gson.JsonObject;

import java.util.List;

/**
 * Hello world!
 *
 */
public class App {
	
	static Counters counters = null;
	static Todo todo = null;
	
	public static void main(String[] args) {

		if (args.length > 0) {
			port(Integer.parseInt(args[0]));
		} else {
			port(8080);
		}

		counters = new Counters();
		todo = new Todo();
		
		after((req, res) -> {
  		  res.type("application/json");
  		});
		
		get("/hello", (req, res) -> "Hello World!");
		
        get("/counters", (req, res) -> counters.toJson());
               
        put("/counters", (req,res) -> {


        	Gson gson = new Gson();
        	
        	counters = gson.fromJson(req.body(), Counters.class);
        
            return counters.toJson();
        	
        });

        get ("/todo", (req, res) -> todo.toJson());
		put ("/todo", (req, res) -> {
			Gson gson = new Gson();
			JsonObject jsonObj = gson.fromJson((req.body()), JsonObject.class);
			String task = jsonObj.get("task").getAsString();

			todo.addTask(task);
			return todo.toJson();

		});

		post ("/todo", (req, res) -> {
			Gson gson = new Gson();
			todo = gson.fromJson(req.body(), Todo.class);
			return todo.toJson();

		});

		delete("/todo", (req, res) -> {
			Gson gson = new Gson();
			JsonObject jsonObj = gson.fromJson((req.body()), JsonObject.class);
			String task = jsonObj.get("task").getAsString();

			todo.removeTask(task);
			return todo.toJson();

		});


	}
    
}
