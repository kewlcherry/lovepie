class Cause < ActiveRecord::Base
  has_many :organisation, :through => :cause_organisations
end
