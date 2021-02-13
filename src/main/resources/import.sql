insert into roles(id, name)
values  (1,'ROLE_USER'),
        (2,'ROLE_MODERATOR'),
        (3,'ROLE_ADMIN');

insert into services(id, service)
values  (1,'REGISTRATION_SERVICE'),
        (2,'EXTEND_SERVICE'),
        (3,'COMPLAINT_SERVICE');

insert into status(id, status)
values  (1,'PENDING'),
        (2,'PROCESSING'),
        (3,'APPROVED')
        (3,'REJECTED');