class DonationsController < ApplicationController
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
      @donation.donation_percentages<<DonationPercentage.new(:cause_id => cause["cause_id"], :percentage => cause[:amount])
    end
  end
  
  def about
    @unpayed = Donation.sum(:amount, :conditions => !:has_been_payed)
    @payed = Donation.sum(:amount, :conditions => :has_been_payed)
  end
end
