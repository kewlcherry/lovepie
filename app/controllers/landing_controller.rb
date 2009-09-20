class LandingController < ApplicationController

  def index
    @organisations = Organisation.all
    @causes = Cause.find_by_mapp
  end
  
  
end
