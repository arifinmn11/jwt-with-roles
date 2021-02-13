package com.arifinmn.projectapi.repositories.impl;

import com.arifinmn.projectapi.models.responses.ScheduleResponse;
import com.arifinmn.projectapi.repositories.ScheduleOtherRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Repository
public class ScheduleOtherRepositoryImpl implements ScheduleOtherRepository {

    @Autowired
    JdbcTemplate jdbcTemplate;

    @Override
    public ScheduleResponse findByTicketId(Integer id) {
        List<ScheduleResponse> customerList = jdbcTemplate.query("SELECT * FROM schedules where ticket_id=?", new RowMapper<ScheduleResponse>() {
            @Override
            public ScheduleResponse mapRow(ResultSet resultSet, int i) throws SQLException {
                ScheduleResponse schedule = new ScheduleResponse();
                schedule.setId(resultSet.getInt("id"));
                schedule.setRoom_id(resultSet.getInt("room_id"));
                schedule.setTicket_id(resultSet.getInt("ticket_id"));
                return schedule;
            }
        }, new Object[]{id});
        return customerList.get(0);
    }

    @Override
    public Boolean existByTicketId(Integer id) {
        Integer countColumn = jdbcTemplate.queryForObject(
                "SELECT count(*) FROM schedules where ticket_id=?", Integer.class, id);
        if (countColumn > 0) {
            return true;
        }
        return false;
    }
}
