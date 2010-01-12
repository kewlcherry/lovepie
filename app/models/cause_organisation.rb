class CauseOrganisation < ActiveRecord::Base
  belongs_to :organisation
  belongs_to :cause
end
