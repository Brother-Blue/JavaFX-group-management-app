package member_manager;

import javafx.beans.property.SimpleStringProperty;

import java.time.LocalDate;

public class Schedule {

    private SimpleStringProperty id;
    private SimpleStringProperty task;
    private LocalDate startDate;
    private LocalDate finishDate;
    private SimpleStringProperty duration;
    private SimpleStringProperty resources;

    public Schedule(String id, String task, LocalDate startDate, LocalDate finishDate, String duration, String resources){

        this.id = new SimpleStringProperty(id);
        this.task = new SimpleStringProperty(task);
        this.startDate = startDate;
        this.finishDate = finishDate;
        this.duration = new SimpleStringProperty(duration);
        this.resources = new SimpleStringProperty(resources);
    }


    public String getTask() {
        return task.get();
    }

    public SimpleStringProperty taskProperty() {
        return task;
    }

    public void setTask(String task) {
        this.task.set(task);
    }

    public LocalDate getStartDate() {
        return startDate;
    }

    public void setStartDate(LocalDate startDate) {
        this.startDate = startDate;
    }

    public LocalDate getFinishDate() {
        return finishDate;
    }

    public void setFinishDate(LocalDate finishDate) {
        this.finishDate = finishDate;
    }

    public String getDuration() {
        return duration.get();
    }

    public SimpleStringProperty durationProperty() {
        return duration;
    }

    public void setDuration(String duration) {
        this.duration.set(duration);
    }

    public String getResources() {
        return resources.get();
    }

    public SimpleStringProperty resourcesProperty() {
        return resources;
    }

    public void setResources(String resources) {
        this.resources.set(resources);
    }

    public String getId() {
        return id.get();
    }

    public SimpleStringProperty idProperty() {
        return id;
    }

    public void setId(String id) {
        this.id.set(id);
    }

}
