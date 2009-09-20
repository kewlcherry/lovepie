class LandingController < ApplicationController
  before_filter :require_user, :only => :add_organisation_to_cause
  
  def index
    @organisations = Organisation.all
    @causes = Cause.find_by_mapp
  end
  
  def add_organisation_to_cause
    @organisation = params[:organisation_id]
    @cause = params[:cause_id]
    mapping = Mapping.new
    mapping.user=@current_user
    mapping.organisation=@organisation
    mapping.cause=@cause
    mapping.save
  end
  
end
