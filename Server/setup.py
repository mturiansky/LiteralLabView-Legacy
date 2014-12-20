from flask import Flask
from flask.ext.sqlalchemy import SQLAlchemy
import os

app = Flask(__name__)
app.secret_key = 'L1t3raLl4bV13w' #USER MUST REPLACE THIS
app.config['SQLALCHEMY_DATABASE_URI'] = 'sqlite:////tmp/llw.db'
app.config['UPLOADS_FOLDER'] = os.path.join(os.getcwd(),'uploads')

db = SQLAlchemy(app)