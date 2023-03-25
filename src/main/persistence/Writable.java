package persistence;

import org.json.JSONObject;

//Interface for the JSONReader and JSONWriter classes
public interface Writable {

    //Codes references Writable interface from https://github.students.cs.ubc.ca/CPSC210/JsonSerializationDemo.git
    // EFFECTS: returns this as JSON object
    JSONObject toJson();
}
