class DonationsController < ApplicationController
  def index
    
  end
  
  def new
    @donation = Donation.new
  end
  
  def create
    @p = parameters[:division]
    d = ActiveSupport::JSON.decode(@p)
  end
  
  def about
    @unpayed = Donation.sum(:amount, :conditions => !:has_been_payed)
    @payed = Donation.sum(:amount, :conditions => :has_been_payed)
  end
end
