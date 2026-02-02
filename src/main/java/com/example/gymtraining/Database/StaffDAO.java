package com.example.gymtraining.Database;

import com.example.gymtraining.Model.*;
import com.example.gymtraining.Menu.*;

import java.sql.*;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

public class StaffDAO {
    private final DateTimeFormatter dateFormat = DateTimeFormatter.ofPattern("dd.MM.yyyy");

    public Member getMember(int ID) {
        String sql = "SELECT * FROM member WHERE member_id = ?";
        Connection connection = DatabaseConnection.getConnection();

        try {
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setInt(1, ID);
            ResultSet resultSet = statement.executeQuery();

            int id = 0;
            String fullName = "";
            int age = 0;
            Date subscriptionDate = new Date(0);

            while (resultSet.next()) {
                id = resultSet.getInt("member_id");
                fullName = resultSet.getString("fullname");
                age = resultSet.getInt("age");
                subscriptionDate = resultSet.getDate("subscription_date");
            }

            resultSet.close();
            statement.close();
            DatabaseConnection.closeConnection(connection);

            return new ChildMember(id, fullName, age, subscriptionDate.toLocalDate().format(dateFormat), "Ilyas");
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null;
    }

    public Trainer getTrainer(int ID) {
        String sql = "SELECT * FROM trainer WHERE trainer_id = ?";
        Connection connection = DatabaseConnection.getConnection();

        try {
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setInt(1, ID);
            ResultSet resultSet = statement.executeQuery();

            int id = 0;
            String fullName = "";
            int age = 0;
            String specialization = "";

            while (resultSet.next()) {
                id = resultSet.getInt("trainer_id");
                fullName = resultSet.getString("fullname");
                age = resultSet.getInt("age");
                specialization = resultSet.getString("specialization");
            }

            resultSet.close();
            statement.close();
            DatabaseConnection.closeConnection(connection);

            return new PersonalTrainer(id, fullName, age, specialization, getMember(1));
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null;
    }

    public void insertMember(Member member) {
        String sql = "INSERT INTO member (member_id, fullname, age, subscription_date) VALUES (?, ?, ?, ?)";
        Connection connection = DatabaseConnection.getConnection();

        try {
            PreparedStatement statement = connection.prepareStatement(sql);

            statement.setInt(1, member.getID());
            statement.setString(2, member.getFullName());
            statement.setInt(3, member.getAge());
            statement.setDate(4, member.getSubscriptionDate());

            statement.executeUpdate();

            statement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        DatabaseConnection.closeConnection(connection);
    }

    public void getAllMembers() {
        String sql = "SELECT * FROM member";
        Connection connection = DatabaseConnection.getConnection();

        try {
            PreparedStatement statement = connection.prepareStatement(sql);
            ResultSet resultSet = statement.executeQuery();

            System.out.println("\n--- ALL MEMBERS FROM DATABASE ---");
            while (resultSet.next()) {
                int id = resultSet.getInt("member_id");
                String fullName = resultSet.getString("fullname");
                int age = resultSet.getInt("age");
                Date subscriptionDate = resultSet.getDate("subscription_date");
                System.out.println("ID: " + id);
                System.out.println("Full name: " + fullName);
                System.out.println("Age: " + age);
                System.out.println("Subscription date: " + subscriptionDate.toLocalDate().format(dateFormat));
                System.out.println("---");
            }
            resultSet.close();
            statement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        DatabaseConnection.closeConnection(connection);
    }

    public void updateMember(Member member) {
        String sql = "UPDATE member SET member_id = ?, fullname = ?, age = ?, subscription_date = ? WHERE member_id = ?";
        Connection connection = DatabaseConnection.getConnection();

        try {
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setInt(1, member.getID());
            statement.setString(2, member.getFullName());
            statement.setInt(3, member.getAge());
            statement.setDate(4, member.getSubscriptionDate());
            statement.setInt(5, member.getID());

            statement.executeUpdate();

            statement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        DatabaseConnection.closeConnection(connection);
    }

    public void deleteMember(int memberId) {
        String sql = "DELETE FROM member WHERE member_id = ?";
        Connection connection = DatabaseConnection.getConnection();

        try {
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setInt(1, memberId);

            statement.executeUpdate();

            statement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        DatabaseConnection.closeConnection(connection);
    }

    public void searchMemberByName(String name) {
        ArrayList<Member> memberList = new ArrayList<>();

        String sql = "SELECT * FROM member WHERE fullname ILIKE ? ORDER BY fullname";
        Connection connection = DatabaseConnection.getConnection();

        try {
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1, "%" + name + "%");
            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()) {
                int id = resultSet.getInt("member_id");
                String fullName = resultSet.getString("fullname");
                int age = resultSet.getInt("age");
                Date subscriptionDate = resultSet.getDate("subscription_date");

                Member member = new ChildMember(id, fullName, age, subscriptionDate.toLocalDate().format(dateFormat), "Ilyas");

                memberList.add(member);
            }

            resultSet.close();
            statement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        DatabaseConnection.closeConnection(connection);

        System.out.println("\n--- ALL MATCHING MEMBERS ---");

        for (Member member: memberList) {
            System.out.println("ID: " + member.getID());
            System.out.println("Full name: " + member.getFullName());
            System.out.println("Age: " + member.getAge());
            System.out.println("Subscription date: " + member.getSubscriptionDate().toLocalDate().format(dateFormat));
            System.out.println("---");
        }
    }

    public void searchMemberByIDs(int lowerID, int upperID) {
        ArrayList<Member> memberList = new ArrayList<>();

        String sql = "SELECT * FROM member WHERE member_id BETWEEN ? AND ?";
        Connection connection = DatabaseConnection.getConnection();

        try {
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setInt(1, lowerID);
            statement.setInt(2, upperID);
            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()) {
                int id = resultSet.getInt("member_id");
                String fullName = resultSet.getString("fullname");
                int age = resultSet.getInt("age");
                Date subscriptionDate = resultSet.getDate("subscription_date");

                Member member = new ChildMember(id, fullName, age, subscriptionDate.toLocalDate().format(dateFormat), "Ilyas");

                memberList.add(member);
            }

            resultSet.close();
            statement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        DatabaseConnection.closeConnection(connection);

        System.out.println("\n--- ALL MATCHING MEMBERS ---");

        for (Member member: memberList) {
            System.out.println("ID: " + member.getID());
            System.out.println("Full name: " + member.getFullName());
            System.out.println("Age: " + member.getAge());
            System.out.println("Subscription date: " + member.getSubscriptionDate().toLocalDate().format(dateFormat));
            System.out.println("---");
        }
    }

    public void insertTrainer(Trainer trainer) {
        String sql = "INSERT INTO trainer (trainer_id, fullname, age, specialization) VALUES (?, ?, ?, ?)";
        Connection connection = DatabaseConnection.getConnection();

        try {
            PreparedStatement statement = connection.prepareStatement(sql);

            statement.setInt(1, trainer.getID());
            statement.setString(2, trainer.getFullName());
            statement.setInt(3, trainer.getAge());
            statement.setString(4, trainer.getSpecialization());

            statement.executeUpdate();

            statement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        DatabaseConnection.closeConnection(connection);
    }

    public void getAllTrainers() {
        String sql = "SELECT * FROM trainer";
        Connection connection = DatabaseConnection.getConnection();

        try {
            PreparedStatement statement = connection.prepareStatement(sql);
            ResultSet resultSet = statement.executeQuery();

            System.out.println("\n--- ALL TRAINERS FROM DATABASE ---");
            while (resultSet.next()) {
                int id = resultSet.getInt("trainer_id");
                String fullName = resultSet.getString("fullname");
                int age = resultSet.getInt("age");
                String specialization = resultSet.getString("specialization");
                System.out.println("ID: " + id);
                System.out.println("Full name: " + fullName);
                System.out.println("Age: " + age);
                System.out.println("Specialization: " + specialization);
                System.out.println("---");
            }
            resultSet.close();
            statement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        DatabaseConnection.closeConnection(connection);
    }

    public void updateTrainer(Trainer trainer) {
        String sql = "UPDATE trainer SET trainer_id = ?, fullname = ?, age = ?, specialization = ? WHERE trainer_id = ?";
        Connection connection = DatabaseConnection.getConnection();

        try {
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setInt(1, trainer.getID());
            statement.setString(2, trainer.getFullName());
            statement.setInt(3, trainer.getAge());
            statement.setString(4, trainer.getSpecialization());
            statement.setInt(5, trainer.getID());

            statement.executeUpdate();

            statement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        DatabaseConnection.closeConnection(connection);
    }

    public void deleteTrainer(int trainerId) {
        String sql = "DELETE FROM trainer WHERE trainer_id = ?";
        Connection connection = DatabaseConnection.getConnection();

        try {
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setInt(1, trainerId);

            statement.executeUpdate();

            statement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        DatabaseConnection.closeConnection(connection);
    }

    public void searchTrainerByName(String name) {
        ArrayList<Trainer> trainerList = new ArrayList<>();

        String sql = "SELECT * FROM trainer WHERE fullname ILIKE ? ORDER BY fullname";
        Connection connection = DatabaseConnection.getConnection();

        try {
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1, "%" + name + "%");
            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()) {
                int id = resultSet.getInt("trainer_id");
                String fullName = resultSet.getString("fullname");
                int age = resultSet.getInt("age");
                String specialization = resultSet.getString("specialization");

                Trainer trainer = new PersonalTrainer(id, fullName, age, specialization, new ChildMember(1, "Fariddin", 17, "01.02.2026", "Rahmatulla"));

                trainerList.add(trainer);
            }

            resultSet.close();
            statement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        DatabaseConnection.closeConnection(connection);

        System.out.println("\n--- ALL MATCHING TRAINERS ---");

        for (Trainer trainer: trainerList) {
            System.out.println("ID: " + trainer.getID());
            System.out.println("Full name: " + trainer.getFullName());
            System.out.println("Age: " + trainer.getAge());
            System.out.println("Specialization: " + trainer.getSpecialization());
            System.out.println("---");
        }
    }

    public void searchTrainerByIDs(int lowerID, int upperID) {
        ArrayList<Trainer> trainerList = new ArrayList<>();

        String sql = "SELECT * FROM trainer WHERE trainer_id BETWEEN ? AND ?";
        Connection connection = DatabaseConnection.getConnection();

        try {
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setInt(1, lowerID);
            statement.setInt(2, upperID);
            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()) {
                int id = resultSet.getInt("trainer_id");
                String fullName = resultSet.getString("fullname");
                int age = resultSet.getInt("age");
                String specialization = resultSet.getString("specialization");

                Trainer trainer = new PersonalTrainer(id, fullName, age, specialization, new ChildMember(1, "Fariddin", 17, "01.02.2026", "Rahmatulla"));

                trainerList.add(trainer);
            }

            resultSet.close();
            statement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        DatabaseConnection.closeConnection(connection);

        System.out.println("\n--- ALL MATCHING TRAINERS ---");

        for (Trainer trainer: trainerList) {
            System.out.println("ID: " + trainer.getID());
            System.out.println("Full name: " + trainer.getFullName());
            System.out.println("Age: " + trainer.getAge());
            System.out.println("Specialization: " + trainer.getSpecialization());
            System.out.println("---");
        }
    }
}