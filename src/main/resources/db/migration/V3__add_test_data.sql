insert into users(id, email, is_moderator, name, password, reg_time) values (1, 'test@yandex.ru', true, 'admin', 'admin', now());
insert into users(id, email, is_moderator, name, password, reg_time) values (2, 'andrei.balabekyan@yandex.ru', true, 'v0s7er', 'admin', now());
insert into posts(id, is_active, moderation_status, text, time, title, view_count, moderator_id, user_id) values (1, true, 'accepted', 'this is the first post', now(), 'first post', 17, 1, 1);
insert into posts(id, is_active, moderation_status, text, time, title, view_count, moderator_id, user_id) values (2, true, 'accepted', 'this is the second post', now(), 'second post', 25, 1, 1);
insert into posts(id, is_active, moderation_status, text, time, title, view_count, moderator_id, user_id) values (3, true, 'accepted', 'this is the third post', now(), 'third post', 5, 1, 2);
insert into post_comments(id, text, time, post_id, user_id) values (1, 'крутой пост', now(), 1, 1);
insert into post_comments(id, parent_id, text, time, post_id, user_id) values (2, 1, 'согласен', now(), 1, 2);
insert into tags(id, name) values(1, 'backend'), (2, 'frontend');
insert into tag2post(id, post_id, tag_id) values (1, 1, 1), (2, 2, 2);
