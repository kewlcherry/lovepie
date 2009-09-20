class DonationsController < ApplicationController
  layout "default"
  before_filter :require_user, :only => [:create]
  
  def index
  end
  
  def new
    @donation = Donation.new
  end
  
  def create
    @donation = Donation.new(params[:donation])
    @donation.user=@current_user
    @donation.has_been_payed=false
    @donation.save
    @causes = ActiveSupport::JSON.decode(params[:causes])
    @causes.each do |cause|
      @donation.donation_percentages<<DonationPercentage.new(:cause_id => cause["cause_id"], :percentage => cause["amount"])
    end
    redirect_to :action => 'about'
  end
  
  def about
    @unpayed = Donation.sum(:amount, :conditions => !:has_been_payed)
    @payed = Donation.sum(:amount, :conditions => :has_been_payed)
    @amounts = DonationPercentage.sum(:percentage, :group => :cause_id, :joins => :cause)
    @causes = Cause.find(@amounts.collect {|sa| sa[0]})
  end
end
