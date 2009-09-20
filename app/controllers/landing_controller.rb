class LandingController < ApplicationController

  def index
    @organisations = Organisation.all
    @causes = Cause.all
  end
  
  
end
