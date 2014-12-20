from setup import app, db
from datetime import datetime

class DataSet(db.Model):
	id = db.Column(db.Integer, primary_key=True)
	date = db.Column(db.String(80))
	screen_img = db.Column(db.String(80))
	camera_img = db.Column(db.String(80))

	def __init__(self,date,screen_img,camera_img):
		self.date = date
		self.screen_img = screen_img
		self.camera_img = camera_img

class PostHandler():
	def make_db(self):
		print "[*] Creating Database!"
		db.create_all()

	def add_data(self,date,screen_img,camera_img):
		d = DataSet(date,screen_img,camera_img)
		db.session.add(d)
		db.session.commit()