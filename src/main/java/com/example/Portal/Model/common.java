package com.example.Portal.Model;

import java.util.List;

public class common {

        private Employee employee;
        private List<Kids> kids;
        private Spouse spouse;


    public Employee getEmployee() {
        return employee;
    }

    public void setEmployee(Employee employee) {
        this.employee = employee;
    }

    public Spouse getSpouse() {
        return spouse;
    }

    public void setSpouse(Spouse spouse) {
        this.spouse = spouse;
    }

    public List<Kids> getKids() {
        return kids;
    }

    public void setKids(List<Kids> kids) {
        this.kids = kids;
    }
}
