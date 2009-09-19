class LandingController < ApplicationController
  def index
    @organisation = Organisation.all
  end
  
  
end
