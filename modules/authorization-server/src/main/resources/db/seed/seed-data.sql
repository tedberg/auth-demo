# password is Bcrypt encode of '123'

INSERT INTO usr (id, email, role, password) VALUES (1, 'ted@acme.com', 'ADMIN', '{bcrypt}$2a$04$QutvaATCzTXTZR5bZWleau0jEvXG0Fedq6GQ33lmFwVene4UFolEq');
INSERT INTO usr (id, email, role, password) VALUES (2, 'bill@acme.com', 'USER', '{bcrypt}$2a$04$enok6RI7YgwJ4RRNK7MiCO95HMWBIh1in3eCOdqJopJeqXYOG/5NC');
INSERT INTO usr (id, email, role, password) VALUES (3, 'dan@acme.com', 'USER', '{bcrypt}$2a$04$2Owf9rA0X/HxCHHXFhbIc.wxnJs0iUiqSsmyJKwf9KLLU5AE4m/Mu');
INSERT INTO usr (id, email, role, password) VALUES (4, 'fred@acme.com', 'USER', '{bcrypt}$2a$04$CCIOb1CmmggyXNY7Xufdbum6cWF2CGQjKf6Ap.GqmEiEyre0Z7VWS');
