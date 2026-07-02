package DeepSkilling.Week1.Algorithms;

class Task {
    int taskId;
    String taskName;
    String status;

    public Task(int taskId, String taskName, String status) {
        this.taskId = taskId;
        this.taskName = taskName;
        this.status = status;
    }

    int getTaskId() {
        return taskId;
    }

    String getTaskName() {
        return taskName;
    }

    String getStatus() {
        return status;
    }

    public String toString() {
        return "TaskId: " + taskId + ", Name: " + taskName + ", Status: " + status;
    }
}

class TaskNode {
    Task task;
    TaskNode next;

    public TaskNode(Task task) {
        this.task = task;
        this.next = null;
    }
}

class TaskLinkedList {
    TaskNode head;

    void add(Task task) {
        TaskNode newNode = new TaskNode(task);
        if (head == null) {
            head = newNode;
            return;
        }
        TaskNode current = head;
        while (current.next != null) {
            current = current.next;
        }
        current.next = newNode;
    }

    Task search(int taskId) {
        TaskNode current = head;
        while (current != null) {
            if (current.task.getTaskId() == taskId) {
                return current.task;
            }
            current = current.next;
        }
        return null;
    }

    void traverse() {
        TaskNode current = head;
        while (current != null) {
            System.out.println(current.task);
            current = current.next;
        }
    }

    boolean delete(int taskId) {
        if (head == null) {
            return false;
        }

        if (head.task.getTaskId() == taskId) {
            head = head.next;
            return true;
        }

        TaskNode current = head;
        while (current.next != null) {
            if (current.next.task.getTaskId() == taskId) {
                current.next = current.next.next;
                return true;
            }
            current = current.next;
        }

        return false;
    }
}

public class Exercise5 {
    public static void main(String[] args) {
        TaskLinkedList taskList = new TaskLinkedList();

        taskList.add(new Task(1, "Design Homepage", "Pending"));
        taskList.add(new Task(2, "Setup Database", "In Progress"));
        taskList.add(new Task(3, "Write API Docs", "Pending"));
        taskList.add(new Task(4, "Deploy to Server", "Completed"));

        System.out.println("Initial Task List:");
        taskList.traverse();

        Task found = taskList.search(3);
        if (found != null) {
            System.out.println("Search Result: " + found);
        } else {
            System.out.println("Task not found");
        }

        taskList.delete(2);
        System.out.println("Task List After Deletion:");
        taskList.traverse();
    }
}