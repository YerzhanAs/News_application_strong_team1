insert into t_news_source (name_source, url_source) VALUES  ('ORDA news', 'https://www.instagram.com/ordakazakhstan/'),
                                                            ('BBC News', 'https://www.bbc.com/news'),
                                                            ('The New York Times', 'https://www.nytimes.com'),
                                                            ('Reuters', 'https://www.reuters.com');

insert into t_news_topic ( description, name_topic) VALUES ('News related to politics', 'Politics'),
                                                           ('News related to technology', 'Technology'),
                                                           ('News related to sports', 'Sports');

insert into t_news (content_news, publish_date, title, news_source_id, news_topic_id)  VALUES
                                                                                           ('The election results were announced today...', '2023-05-17 10:57:55.000000', 'Breaking News: Election Results Announced', 1, 1),
                                                                                           ('Scientists have made significant progress in', '2023-05-02 14:15:00', 'New Technology Advancements', 2, 2),
                                                                                           ('Thrilling Football Match Ends in Draw', '2023-05-03 19:00:00', 'Thrilling Football Match Ends in Draw', 3,3),
                                                                                           ('Here are some useful tips for maintaining a healthy lifestyle...', '2023-05-04 08:45:00', 'Latest Health Tips for a Healthy Lifestyle', 3, 2),
                                                                                           ('Political Situation in Kazakhstan...', '2023-05-04 08:45:00', 'New Kazakhstan', 1, 2);

insert into roles (name) VALUES ('ROLE_USER'),
                                ('ROLE_ADMIN'),
                                ('ROLE_MODERATOR');

