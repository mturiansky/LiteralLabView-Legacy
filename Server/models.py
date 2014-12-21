from setup import app, db
from datetime import datetime

class DataSet(db.Model):
	id = db.Column(db.Integer, primary_key=True)
	date = db.Column(db.String(80))
	project_name = db.Column(db.String(80))
	screen_img = db.Column(db.String(80))
	camera_img = db.Column(db.String(80))

	def __init__(self,date,project_name,screen_img,camera_img):
		self.date = date
		self.project_name = project_name
		self.screen_img = screen_img
		self.camera_img = camera_img

class PostHandler():
	def make_db(self):
		print "[*] Creating Database!"
		db.create_all()

	def add_data(self,date,project_name,screen_img,camera_img):
		d = DataSet(date,project_name,screen_img,camera_img)
		db.session.add(d)
		db.session.commit()