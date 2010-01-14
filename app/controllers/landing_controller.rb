class LandingController < ApplicationController

  def index
    @causes = Cause.all
  end
end