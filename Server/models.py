from setup import app, db
from datetime import datetime
from flask.ext.login import UserMixin

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

class User(db.Model, UserMixin):
	id = db.Column(db.Integer, primary_key=True)
	name = db.Column(db.String(80))
	passwd = db.Column(db.String(80))
	auth = db.Column(db.Integer)

	def __init__(self, name, passwd, auth=0):
		self.name = name
		self.passwd = passwd
		self.auth = auth

	def is_authenticated(self):
		return self.auth

	def auth_toggle(self):
		if self.auth:
			self.auth = 0
		else:
			self.auth = 1
		db.session.commit()

class PostHandler():
	def make_db(self):
		print "[*] Creating Database!"
		db.create_all()
		db.session.add(User('admin', 'admin'))
		db.session.commit()

	def add_data(self,date,project_name,screen_img,camera_img):
		d = DataSet(date,project_name,screen_img,camera_img)
		db.session.add(d)
		db.session.commit()

	def get_recent(self):
		count = DataSet.query.count()
		return DataSet.query.get(count)

	def search_data(self,inp):
		results = []
		for a in DataSet.query.all():
			if inp in a.date or inp in a.project_name:
				if a not in results:
					results.append(a)
		return results

	def get_user(self,id):
		return User.query.get(id)

	def verify_user(self,name,password):
		user = User.query.filter_by(name=name).first()
		if user and password == user.passwd:
			return user
