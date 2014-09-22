package com.codeminders.labs.timeextractor.rest.entities;

import java.util.Locale;

import com.codeminders.labs.timeextractor.temporal.entites.Temporal;

public class DurationHtml implements HtmlTemporal {
	private int seconds;
	private int minutes;
	private int hours;
	private int days;
	private int weeks;
	private int months;
	private int years;

	private Locale locale;
	private double confidence;

	public DurationHtml(Temporal temporal, Locale locale, double confidence) {
		days = temporal.getDuration().getDays();
		hours = temporal.getDuration().getHours();
		minutes = temporal.getDuration().getMinutes();
		seconds = temporal.getDuration().getSeconds();
		years = temporal.getDuration().getYears();
		weeks = temporal.getDuration().getWeeks();
		months = temporal.getDuration().getMonths();
		this.locale = locale;
		this.confidence = confidence;
	}

	public int getDays() {
		return days;
	}

	public void setDays(int days) {
		this.days = days;
	}

	public int getHours() {
		return hours;
	}

	public void setHours(int hours) {
		this.hours = hours;
	}

	public int getMinutes() {
		return minutes;
	}

	public void setMinutes(int minutes) {
		this.minutes = minutes;
	}

	public int getSeconds() {
		return seconds;
	}

	public void setSeconds(int seconds) {
		this.seconds = seconds;
	}

	public int getYears() {
		return years;
	}

	public void setYears(int years) {
		this.years = years;
	}

	public int getWeeks() {
		return weeks;
	}

	public void setWeeks(int weeks) {
		this.weeks = weeks;
	}

	public int getMonths() {
		return months;
	}

	public void setMonths(int months) {
		this.months = months;
	}

	public Locale getLocale() {
		return locale;
	}

	public void setLocale(Locale locale) {
		this.locale = locale;
	}

	public double getConfidence() {
		return confidence;
	}

	public void setConfidence(double confidence) {
		this.confidence = confidence;
	}

	@Override
	public String toString() {
		return "Duration [days=" + days + ", hours=" + hours + ", minutes="
				+ minutes + ", seconds=" + seconds + ", years=" + years
				+ ", weeks=" + weeks + ", months=" + months + "]";
	}

}
