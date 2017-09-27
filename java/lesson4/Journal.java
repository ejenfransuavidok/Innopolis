package lesson4;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

public class Journal implements Serializable {
    private Lesson lesson;
    private Set<Student> presentSet = new HashSet<>();
    private Set<Group> groups = new HashSet<>();


    public Set<Group> getGroups() {
        return groups;
    }

    public void setGroups(Set<Group> groups) {
        this.groups = groups;
    }

    public Journal(Lesson lesson, Set<Student> presentSet) {
        this.lesson = lesson;
        this.presentSet = presentSet;
    }

    public Lesson getLesson() {
        return lesson;
    }

    public void setLesson(Lesson lesson) {
        this.lesson = lesson;
    }

    public Set<Student> getPresentSet() {
        return presentSet;
    }

    public void setPresentSet(Set<Student> presentSet) {
        this.presentSet = presentSet;
    }

}
