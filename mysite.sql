--#users 테이블 삭제
drop table users;

--#users 시퀀스 삭제 seq_users_no
drop sequence seq_users_no;

--#users 테이블 생성 
create table users(
         no number,
         id varchar2(20) unique not null,
         password varchar2(20) not null,
         name varchar2(20),
         gender varchar2(10), 
         primary key(no)
);
--#users 시퀀스 생성 
create sequence seq_users_no
increment by 1
start with 1
nocache;

--#insert문 2개
insert into users
values (seq_users_no.nextval,'hello_id','1234','장서윤','female');

insert into users
values (seq_users_no.nextval,'start','2345','유재석','male');

commit;

--#select문
select  no,
        id,
        password,
        name,
        gender
from users;

--#login id,password 데이터 가져오기
select  no,
        name
from users
where id = 'pink'
and password = '3456';

--#update 수정
update users
set password = '3456',
    name = '핑크',
    gender = 'female'
where no = '4';

--#getPerson 한명의 데이터 호출
select  id,
        password,
        name,
        gender
from users
where no = '4';
---------------------------------------------------------------------
--#board테이블 삭제 
drop table board;

--#시퀀스 삭제 
drop sequence seq_board_no;

--#board테이블 생성
create  table board(
        no number,
        title varchar2(500) not null,
        content varchar2(4000),
        hit number,
        reg_date date not null,
        user_no number not null,
        primary key(no),
        constraint board_fk foreign key (user_no)
        references users(no)
);

--#시퀀스 생성
create sequence seq_board_no
increment by 1
start with 1
nocache;

commit;

--#insert 등록
insert into board
values(seq_board_no.nextval,'장서윤의 게시판입니다','장서윤의 게시판 내용입니다 ',0,sysdate,1);

insert into board
values(seq_board_no.nextval,'이승기의 게시판입니다','이승기의 게시판 내용입니다 ',0,sysdate,2);

insert into board
values(seq_board_no.nextval,'네이버의 게시판입니다','네이버의 게시판 내용입니다 ',0,sysdate,3);

insert into board
values(seq_board_no.nextval,'구글의 게시판입니다','네이버의 게시판 내용입니다 ',0,sysdate,4);

commit;

--#board select 테이블 전체 확인
select no,
        title,
        content,
        hit,
        reg_date,
        user_no
from board;

--#board+users 테이블 조인 출력
select  board.no bNo,
        users.no uNo,
        board.user_no user_no,
        board.title title,
        users.name name,
        board.hit hit,
        to_char(board.reg_date, 'yy-mm-dd HH24:MI') reg_date
from users , board
where users.no = board.user_no
order by board.reg_date desc;

--#해당 게시판 정보 불러오기
select  users.name name,
        board.hit hit,
        to_char(board.reg_date, 'yyyy-mm-dd') reg_date,
        board.title title,
        board.content content,
        board.no bNo
from users, board
where users.no = board.user_no
and board.no = 7;

--#update 수정
update board
set title = '타이틀 수정전',
    content = '컨텐츠 수정전'
where no = 7;

--#delete 삭제
delete from board
where no = 1;

--#hit 조회수 올리기
update board
set hit = hit+1
where board.no = 1;
