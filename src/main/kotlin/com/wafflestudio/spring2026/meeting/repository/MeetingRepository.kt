package com.wafflestudio.spring2026.meeting.repository

import com.wafflestudio.spring2026.meeting.model.Meeting
import org.springframework.stereotype.Repository

@Repository
class MeetingRepository {
    // ponytail: sequential seminar store; replace with a database-backed repository before concurrent use.
    // 등록 순서를 유지하기 위해 LinkedHashMap을 사용한다.
    private val meetings = linkedMapOf<Long, Meeting>()
    private var nextId = 1L

    fun save(
        title: String,
        capacity: Int,
    ): Meeting {
        val meeting = Meeting(
            id = nextId,
            title = title,
            capacity = capacity,
        )

        nextId += 1
        meetings[meeting.id] = meeting

        return meeting
    }

    fun findAll(): List<Meeting> = meetings.values.toList()

    fun findById(id: Long): Meeting? = meetings[id]

    fun update(meeting: Meeting): Meeting {
        meetings[meeting.id] = meeting

        return meeting
    }

    /** 삭제에 성공하면 true, 대상이 없으면 false를 돌려준다. */
    fun deleteById(id: Long): Boolean = meetings.remove(id) != null
}
