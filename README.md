# MessBook

# Entities

## Student

| roll number  | primary key  |
| --- | --- |
| name |   |
| phone number |  |
| department  | foreign key  |
| password |  |
| cmail_id |  |
| isGraduated |  |
| gender  |  |
| remainder ( the amount remaining at the end of the semester ) |  |
| student_image_url |  |

## Department

| name  |  |
| --- | --- |
| hod  |  |
|  |  |

## Hostel

| name | primary key  |
| --- | --- |
| warden_name |  |
| warned_phone |  |
| gender |  |

## Mess

| id  | primary key  |
| --- | --- |
| name |  |
| hostel name | foreign key  |
| phone_number |  |
| cmail_id |  |
| password |  |
| due  |  |
| mess_image_url |  |

## Semester_Details

| id  | primary key |
| --- | --- |
| start_date  |  |
| end_date  |  |
| price_per_meal |  |
| mess_advance_price |  |

## Extra Item Menu

| semester_details_id | foreign key  |
| --- | --- |
| name  | primary key  |
| price |  |

## Semester_Info ( students )

| semester_details_id | foreign key  |
| --- | --- |
| student roll number  | foreign key  |
| hostel name  | foreign key |
| mess_id  | foreign key  |
| semester number |  |
| mess_advance_paid |  |

## Transactions

| paid_to | primary key  |
|---|---|---|
| amount |  |  |
| date  |  |  |
| done |  |  |

## Mess Absent

once inserted in the database, it cannot be deleted 

and duplicate and intersecting entries will not be entertained 

| student roll num | foreign key | prim key |
| --- | --- | --- |
| semester_id | foreign key  | prim key |
| mess_id  | foreign key  |  |
| start_date  |  |  |
| end_date |  |  |

## Mess Extra Entry

| student roll num | foreign key  |  |
| --- | --- | --- |
| semester_id | foreign key  |  |
| mess_id  | foreign key |  |
| item_name | foreign key  |  |
| date |  |  |

## Invoice

The invoice will be handled in the following manner 

1. student 
    1. it will be calculated from the entires 
2. messs 
    1. it will be calculated from the students 
3. admin
    1. we will have dues and the messe’s invoice 

## Feedback

| student_id | pk 1 fk |
| --- | --- |
| semester_id | pk 2 fk |
| mess_id | pk 3 fk |
| month_of_comment | pk 4  |
| text |  |

## Mail

- id
- sender_cmail
- receiver_cmail
- sending_date
- hasDelivered
- hasRead

## Notification

| id | primary key  |
| --- | --- |
| owner_id |  |
| semester_id | foreign key  |
| title |  |
| date  |  |
| text |  |

## Mess Change Application

| mess_id  | fk |
| --- | --- |
| student roll  | fk |
| reason |  |
| status  |  |
