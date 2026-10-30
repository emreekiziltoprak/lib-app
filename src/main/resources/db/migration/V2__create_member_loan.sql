

create table member (
id uuid primary key,
full_name varchar(255) not null,
email varchar(255) not null,
created_at timestamp not null
);

create table loan (
id uuid primary key,
book_id uuid not null references books(id),
member_id uuid not null references member(id),
borrowed_at date not null,
returned_at date,
due_date date not null,
renew_count int not null
);

create index idx_loan_member on loan (member_id);
create index idx_loan_book on loan (book_id);