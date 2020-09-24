package no.hvl.dat110.rest.counters;

import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.List;

public class Todo {


    private List<String> todoList = new ArrayList<String>(); // maps todos to done:

    public Todo () {
    }

    public Todo (List<String> todoList) {
        this.todoList = todoList;
    }

    public void addTask(String task) {
        this.todoList.add(task);
    }

    public void removeTask(String task) {
        this.todoList.remove(task);
    }

    public List<String> getTodoList() {
        return this.todoList;
    }

    public void setTodoList(List<String> todoList) {
        this.todoList = todoList;
    }

    String toJson () {

        Gson gson = new Gson();

        String jsonInString = gson.toJson(this);

        return jsonInString;
    }
}