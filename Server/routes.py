from flask import render_template, redirect, url_for, request, send_from_directory, flash
from setup import app
from models import PostHandler as PH

@app.route('/')
def home():
	return render_template('index.html')