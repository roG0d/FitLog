package a.darkr.fitlog1;

import java.util.List;

public class Routine {


    private String name;
    private static List<Exercise> routine;

    public Routine(String name, List<Exercise> routine) {
        this.name = name;
        this.routine = routine;
    }


    public String getName() {
        return name;
    }

    public List<Exercise> getRoutine() {
        return routine;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setRoutine(List<Exercise> routine) {
        this.routine = routine;
    }

   static public boolean addExercise(Exercise e){
        boolean res=false;
        if(e!=null){
            routine.add(e);
            res=true;
        }
        return res;
    }

         public boolean deleteExercise(Exercise e){
        boolean res=false;
        if(this.routine.contains(e)){
            routine.remove(e);
            res=true;
        }
        return res;
    }
}
