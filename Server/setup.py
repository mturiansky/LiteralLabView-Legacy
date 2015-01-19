from flask import Flask
from flask.ext.sqlalchemy import SQLAlchemy
from flask.ext.login import LoginManager, AnonymousUserMixin
import os

app = Flask(__name__)
app.secret_key = 'L1t3raLl4bV13w' #USER MUST REPLACE THIS
app.config['COMMUNICATION_KEY'] = '804e33bcd2dfc0cb435fe64ce20646fe' #MUST BE SAME AS CLIENT
app.config['SQLALCHEMY_DATABASE_URI'] = 'sqlite:////tmp/llw.db'
app.config['UPLOADS_FOLDER'] = os.path.join(os.getcwd(),'uploads')

db = SQLAlchemy(app)

lm = LoginManager()
lm.login_view = "login"
lm.login_message = u"Please log in to access this page."
AnonymousUserMixin.name = u'Anonymous'
lm.anonymous_user = AnonymousUserMixin
lm.setup_app(app)
